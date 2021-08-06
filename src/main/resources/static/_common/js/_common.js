var	$window			= null,
	$document		= null,
	$html			= null,
	$body			= null,
	$html_body		= null,
	$wrapper		= null,
	$header			= null,
	$activeFocus	= null,
_;

var popupOptions = {
	$popArr: [],
	zIndexUnit: 1000,
}
function popupOpen(id, callback){
	var $popWrap = $('#'+id);
	var $focus = $popWrap.find('.popup-focus');

	//팝업이 남아있는 경우
	if (popupOptions.$popArr.length){
		$activePopWrap = popupOptions.$popArr[popupOptions.$popArr.length - 1];
		$activePopWrap.addClass('is-disabled');
	}

	$popWrap.data('opener', $activeFocus).addClass('is-active');

	//다중팝업설정
	popupOptions.$popArr.push($popWrap);
	var zIndex = popupOptions.zIndexUnit + popupOptions.$popArr.length;
	$popWrap.css({'z-index':zIndex});
}

function popupClose(id, callback){
	var $popWrap = $('#'+id);
	var $focus = $popWrap.data('opener');
	var $activePopWrap = null;
	$popWrap.removeClass('is-dimmer is-active').removeAttr('style');
	
	//다중팝업 설정
	popupOptions.$popArr.pop();
	
	//팝업이 남아있는 경우
	if (popupOptions.$popArr.length){
		//팝업활성화
		$activePopWrap = popupOptions.$popArr[popupOptions.$popArr.length - 1];
		$activePopWrap.removeClass('is-disabled');

		//접근성초점
		$document.off('focusin.popupEvent click.popupEvent').on('focusin.popupEvent click.popupEvent', function(e){
			if ($activePopWrap.has(e.target).length === 0) { 
				$lastFocus = $activePopWrap.data('opener');
				$lastFocus.focus();
			}
		});
	} 
}

/* Popover */
function popoverInit(){
	$document.off('click.popoverEvent').on('click.popoverEvent', '.js-popover', function(e){
		popoverOpen($(this).data('id'));
	});
	$document.off('focusin.popoverEvent2 click.popoverEvent2').on('focusin.popoverEvent2 click.popoverEvent2', function(e){
		$('div.popover.is-active').each(function(){
			var id = $(this).attr('id');
			if ($('div.popover-area').has(e.target).length === 0 && !$(e.target).hasClass('.js-popover')){
				popoverClose(id);
			}
		})
	});
}
function popoverOpen(id, callback){
	$('div.popover.is-active').each(function(){ popoverClose($(this).attr('id')) });
	$('#'+id).addClass('is-active');
	console.log($('#'+id).hasClass('is-active'));
	$('button[data-id='+id+']').addClass('is-active').attr({'aria-expanded':'true'});
	setCallback(callback);
}
function popoverClose(id, callback){
	$('#'+id).removeClass('is-active');
	$('button[data-id='+id+']').removeClass('is-active').attr({'aria-expanded':'false'});
	setCallback(callback);
}

