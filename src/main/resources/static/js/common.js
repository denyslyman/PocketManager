// Preloader
window.addEventListener('load', function () {
	setTimeout(function () {
		$('.preloader').fadeOut('slow');
		$('html').removeClass('body-blocked');
		$('body').addClass('loaded');
	}, 1500);
});

// Password hide toggle
$(".password-toggle").click(function() {
	$(this).toggleClass("password-toggle_show password-toggle_hide");
	var inputPassword = $($(this).attr("toggle"));
	if (inputPassword.attr("type") == "password") {
		inputPassword.attr("type", "text");
	} else {
		inputPassword.attr("type", "password");
	}
});