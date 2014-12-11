$(function() {
		//setup mute button
	

	$('.section-wrapper').on('click', '.mute-wrapper',function() {	
		$(this).toggleClass('muted-wrapper unmuted-wrapper');
	});
	    // setup master volume
	    $(".master").slider({
			value : 60,
			orientation : "horizontal",
			range : "min",
			animate : true
	    });
	    // setup graphic EQ
	    $(".level").each(function() {
			// read initial values from markup and remove that
			var value = parseInt($(this).text(), 10);
			$(this).empty().slider({
			    value : value,
			    range : "min",
			    max : 100,
			    animate : true,
			    orientation : "vertical"
			});
	    });
	    $('.pwrbtn').bind('click', function() {
			$(this).toggleClass('active');
	    });
	    $( window ).resize(function() {
		  if($( window ).width() < 768){
		      setupMobile();
		  }else{
		      setupDesktop();
		  }
		});
	    var viewwidth = $(window).width(); 
	    	
		if(viewwidth < 768){
		    setupMobile();
		    
		 }
		
		    
		  
		// scripts
	function setupMobile(){
	    if($('section').hasClass('col-md-4')){
		$('section').removeClass('col-md-4');
		$('.section-wrapper').panelSnap();
		
	    }
	}
	function setupDesktop(){
	    if(!$('section').hasClass('col-md-4')){
		$('section').addClass('col-md-4');
		$('.section-wrapper').panelSnap('destroy');
	    }
	}
	});