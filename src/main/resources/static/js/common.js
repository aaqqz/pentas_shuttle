/**
 *
 * 32자리의 랜덤한 uuid 를 생성하는 javascript 함수
 * 예) b9e2e10b6aa2132779b9786d55e2b223
 *
 * @returns
 */
function cf_genUUID(){
    return cf_genrandom()+cf_genrandom()+cf_genrandom()+cf_genrandom()+cf_genrandom()+cf_genrandom()+cf_genrandom()+cf_genrandom();
}


/**
 * 4자리의 랜덤한 영어및 숫자의 값을 생성한다.
 * 예) fbb1
 * @returns
 */
function cf_genrandom(){
    return ((1+Math.random()) * 0x10000 | 0).toString(16).substring(1);
}

function cf_onlyNum(obj){
    obj.value = obj.value.replace(/[^0-9.]/g,'').replace(/(\..*)\./g, '$1');
}

function cf_onlyNumFormat(obj){
    var tmp = obj.value.replace(/[^0-9.]/g,'').replace(/(\..*)\./g, '$1');
    if(tmp === ""){
        obj.value = tmp;
    } else {
        obj.value = Number(tmp).numformat();
    }
}

/**
 * ajax 통신함수
 *
 * @param url
 * @param params
 * @param callback
 * @returns
 */
var rsltFailArr = ['error', 'user-error', 'FAIL', 'sys-error'];
function cf_call(url, params, callback, isloadingbar, options){

    if(cf_isEmpty(options)) options = {
        headers: {
            // 'Content-Type': 'application/json;charset=UTF-8',
            // "Access-Control-Allow-Origin": "*",
        }
    };

    if(isloadingbar !== false){
        cf_loadingbarShow();
    }

    axios.post(url, params, options)
        .then(function (response) {
            cf_loadingbarHide();
            if(rsltFailArr.includes(response.data.rsltStat)){
                if(response.data.rsltStat == "user-error" && !cf_isEmpty(response.data.errMsg)){
                    alert(response.data.errMsg);
                } else {
                    alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
                }
            } else {
                if(callback != null){
                    if(cf_whatIsIt(response.data) === "string" && response.data.indexOf("<!DOCTYPE html>") != -1){
                        alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
                    } else {
                        callback(response.data);
                    }
                }
            }
        })
        .catch(function (error) {
            cf_loadingbarHide();
            if(error.message == "Network Error"){
                alert("네트워크상태 또는 서버 구동상태를 확인해 주세요.");
            } else {
                alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
            }
            console.log(error);
        });
}

/**
 * 파일을 업로드를 동반한 호출함수
 *
 * @param filesArr
 * @param param
 * @param callback
 * @returns
 */
function cf_callWithFiles(url, fileList, param, callback){

    cf_loadingbarShow();

    var formData = new FormData();
    for(var i=0; i<fileList.length; i++){
        formData.append("fileList", fileList[i]);
    }
    formData.append("param",JSON.stringify(param));

    $.ajax({
        url : url,
        dataType : "json",
        data : formData,
        type : 'POST',
        contentType : false,
        processData : false,
        success: function(data, status){
            cf_loadingbarHide();
            if(rsltFailArr.includes(data.rsltStat)){
                if(data.rsltStat == "user-error" && !cf_isEmpty(data.errMsg)){
                    alert(data.errMsg);
                } else {
                    alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
                }
            } else {
                if(callback != null){
                    callback(data);
                }
            }
        },
        error: function(){
            cf_loadingbarHide();
            alert("업로드를 실패했습니다. 서버상태를 확인해 주세요.");
        }
    })
}

/**
 * 해당 파라메타가 비어있는지 확인
 *
 * @param obj
 * @returns
 */
function cf_isEmpty(obj){
    var objtyp = cf_whatIsIt(obj);
    if(objtyp == "null") return true;
    else if(objtyp == "undefined") return true;
    else if(objtyp == "string" && obj.trim() == "") return true;
    else if(objtyp == "object" && obj == {}) return true;
    return false;
}

/**
 * 해당객체의 타입명을 반환해주는 함수
 *
 * @param obj
 * @returns
 */
function cf_whatIsIt(obj){
    var stringConstructor = "test".constructor;
    var numberConstructor = Number("123").constructor;
    var arrayConstructor = [].constructor;
    var objectConstructor = {}.constructor;

    if(obj === null){
        return "null";
    } else if(obj === undefined){
        return "undefined";
    } else if(obj.constructor === stringConstructor){
        return "string";
    } else if(obj.constructor === numberConstructor){
        if(isNaN(obj)) return "nothing";
        return "number";
    } else if(obj.constructor === arrayConstructor){
        return "array";
    } else if(obj.constructor === objectConstructor){
        return "object";
    } else {
        return "nothing";
    }
}

function cf_loadingbarShow(){

}

function cf_loadingbarHide(){

}

function telePhoneCheck(obj) {
    obj.value = obj.value.replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/,"$1-$2-$3").replace("--", "-");
}
