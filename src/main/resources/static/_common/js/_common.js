

var	$window			= null,
$document		= null,
$html			= null,
$body			= null,
$html_body		= null,
$wrapper		= null,
$header			= null,
$activeFocus	= null,
_;
/* 엘리먼트 설정 */
function setElementInit(){
$window		= $(window);
$document	= $(document);
$html		= $('html');
$body		= $('body');
$html_body	= $('html, body');
$wrapper	= $('.wrapper');
$header		= $('.header');
$document.off('focusin.eleEvent click.eleEvent').on('focusin.eleEvent click.eleEvent', function(e){
	$activeFocus = $(e.target);
})
}



/* 상태 설정 */
function setStatusInit(){
scrTop = scrTopStart = $window.scrollTop(); // 스크롤 현재상태
setPosition(scrTop); // 스크롤 현재상태 설정
setScrolled(); // 스크롤 진행상태 설정
setResized(); // 리사이징 진행상태 설정
}
function setPosition(val){
//스크롤 처음확인
if (val == 0){
	isScrFirst = true;
	$body.addClass('is_scrollFirst');
} else {
	isScrFirst = false;
	$body.removeClass('is_scrollFirst');
}
//스크롤 마지막확인
if (val + $window.outerHeight() == $document.height()){
	isScrLast = true;
	$body.addClass('is_scrollLast');
} else {
	isScrLast = false;
	$body.removeClass('is_scrollLast');
}
}

function setScrolled(){
var scrollEndTime;
var isScrolled = false;
var oldScrTop = scrTop;
$window.off('scroll.customEvent').on('scroll.customEvent', function(){
	var curScrTop = $window.scrollTop();

	//스크롤 방향
	if (oldScrTop > curScrTop){
		$window.trigger('scrollUp');
		$body.addClass('is_scrollUp').removeClass('is_scrollDown');
	} else if (oldScrTop < curScrTop){
		$window.trigger('scrollDown');
		$body.addClass('is_scrollDown').removeClass('is_scrollUp');
	}
	oldScrTop = curScrTop;

	//스크롤 종료
	clearTimeout(scrollEndTime);
	scrollEndTime = setTimeout(function(){
		isScrolled = false;
		scrTop = scrTopEnd = curScrTop;
		$window.trigger('scrollEnd');
	}, 100);

	setPosition(curScrTop);
});
}
function setResized(){
//Resized
var resizeEndTime;
$window.off('resize.customEvent').on('resize.customEvent', function(){
	clearTimeout(resizeEndTime);
	resizeEndTime = setTimeout(function(){
		$window.trigger('resizeEnd');
	}, 100);
});
}

/*---------------------------------------------------------------
@Utility
---------------------------------------------------------------*/
/* 스크롤설정 */
var setScrollOptions = {
clsLockAll: 'is-locked-all',
clsLockIOS: 'is-locked-ios',
scrTop: null
}
/* 스크롤 비활성 */
function setScrollDisable() {
if (isIOS){
	setScrollOptions.scrTop = $window.scrollTop();
	$html_body.addClass(setScrollOptions.clsLockIOS);
	$wrapper.css({position: 'relative', top: this.scrTop * (-1)});
} else {
	$html_body.addClass(setScrollOptions.clsLockAll);
}
}
/* 스크롤 활성화 */
function setScrollEnable(){
if (isIOS){
	$html_body.removeClass(setScrollOptions.clsLockIOS);
	$wrapper.removeAttr('style');
	$window.scrollTop(setScrollOptions.scrTop);
} else {
	$html_body.removeClass(setScrollOptions.clsLockAll);
}
}

/* 스트링을 스크립트 코드로 변환 */
function getNewFunc($ele){
var callback = $ele.data('callback');
//속성으로 콜백함수 처리
if (callback != '' && callback != undefined){
	return (new Function(str))();
}
}

/* callback이 존재하면 실행 */
function setCallback(callback){
if (callback){ typeof(callback) == 'function' ? callback() : callback; }
}






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
	if (popupAlert) {
		console.log('드러옴');

		
	}
	//다중팝업 설정
	popupOptions.$popArr.pop();

	//팝업이 남아있는 경우
	if (popupOptions.$popArr.length){
		//팝업활성화
		$activePopWrap = popupOptions.$popArr[popupOptions.$popArr.length - 1];
		$activePopWrap.removeClass('is-disabled');

		//접근성초점
		// $document.off('focusin.popupEvent click.popupEvent').on('focusin.popupEvent click.popupEvent', function(e){
		// 	if ($activePopWrap.has(e.target).length === 0) { 
		// 		$lastFocus = $activePopWrap.data('opener');
		// 		$lastFocus.focus();
		// 	}
		// });
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











/* Selected */
function selectInit(){ 
$document.off('click.selEvent').on('click.selEvent', '.js-select', function(e){ 
	$(this).addClass('is-active').attr('aria-active', 'true').siblings().removeClass('is-active').attr('aria-selected', 'false');
});
}
/* Tab */
function tabInit(){
$document.off('click.tabEvent').on('click.tabEvent', '.js-tab', function(e){
	var callback = function(){ getNewFunc($(this)) };
	tabAction($(this).attr('aria-controls'), callback);
});
}
function tabAction(id, callback){
var $eleTabItem = $('[aria-controls='+id+']');
var $eleTabPanel = $('#'+ id);
$eleTabItem.addClass('is-active').attr('aria-selected','true').siblings().removeClass('is-active').attr('aria-selected','false');
$eleTabPanel.addClass('is-active').siblings().removeClass('is-active');
setCallback(callback);
}


/* Ready */
$(function(){
set_init();
ui_init();
});

/* Setting */
function set_init(){
setElementInit(); // 엘리먼트 설정
setStatusInit(); // 상태 설정
}


/* UI */
function ui_init(){
/* Layout */
// asideInit(); // 사이드메뉴

/* Components */
selectInit(); // Selected
tabInit(); // Tab
// dropInit(); // Dropmenu
// accoInit();	// Accodion
// tooltipInit(); // Tooltip
// popoverInit(); // Popover
}

