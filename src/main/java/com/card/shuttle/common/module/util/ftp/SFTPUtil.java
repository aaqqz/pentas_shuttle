package com.card.shuttle.common.module.util.ftp;

import com.card.shuttle.common.module.MimeTypeConstants;
import com.card.shuttle.common.module.util.CmmnUtil;
import com.card.shuttle.common.module.util.DevMap;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class SFTPUtil {
	
	/**
	 * SFTP File download 함수
	 * @param request
	 * @param response
	 * @param param
	 */
	public void fileDownload(HttpServletRequest request, HttpServletResponse response, DevMap param) {
		
		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;
		
		try {
			String id = param.getString("id");
			String pw = param.getString("pw");
			String addr = param.getString("addr");
			String path = param.getString("path");
			String fileNm = param.getString("fileNm");
			String port = param.getString("port");
			if(port == null || port.trim().equals("")) {
				port = "22";
			}
			log.debug(">>>> SFTP info <<<<");
			log.debug("=> id :" + id);
			log.debug("=> pw :" + pw);
			log.debug("=> addr :" + addr);
			log.debug("=> path :" + path);
			log.debug("=> fileNm :" + fileNm);
			log.debug("=> port :" + port);
			
			JSch jsch = new JSch();
			session = jsch.getSession(id, addr, Integer.parseInt(port));
			session.setPassword(pw);
			
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			
			session.setConfig(config);
			session.connect();
			log.debug(">>>> SFTP Session connect! ");
			
			channel = session.openChannel("sftp");
			channel.connect();
			log.debug(">>>> SFTP Channel connect! ");
			
			channelSftp = (ChannelSftp) channel;
			
			channelSftp.cd(path);
			log.debug(">>>> STPF change directory");
			
			try(InputStream is = channelSftp.get(fileNm);
					BufferedInputStream in = new BufferedInputStream(is);
					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())){
//				String mimetype = "application/x-msdownload";
				String mimetype = MimeTypeConstants.getMimeType(fileNm);
				if(mimetype == null) mimetype = "application/x-msdownload";
				response.setContentType(mimetype);
				CmmnUtil.setContectDispositionResponse(fileNm, request, response);
				FileCopyUtils.copy(in, out);
				out.flush();
			}
		} catch(JSchException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(SftpException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} catch(IOException ex) {
			log.error(CmmnUtil.getExceptionMsg(ex));
		} finally {
			if(channelSftp != null) channelSftp.quit();
			log.debug(">>>> SFTP Channel quit");
			if(session != null) session.disconnect();
			log.debug(">>>> SFTP session disconnect");
		}
	}
}
