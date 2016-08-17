$.fn.max = function(selector) { 
    return Math.max.apply(null, this.map(function(index, el) { return selector.apply(el); }).get() ); 
};

/* jQuery's document.ready function will NOT work in XSLT rendered pages,
   so this event has to be invoked from the end of the rendered HTML instead */
function runFixSize(){
	fixSize();

	$(window).resize(function(){
		fixSize();
	});
}

function fixSize(){
	$('div.div-cell').attr("style", "");
	$('div.div-cell').height(function () {
	  var maxHeight = $(this).closest('div.row').find('div.div-cell')
					  .max( function () {
						return $(this).outerHeight();
					  });
	  return maxHeight;
	});
}
