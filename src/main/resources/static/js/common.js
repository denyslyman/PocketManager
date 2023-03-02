// Preloader
window.addEventListener('load', function () {
	setTimeout(function () {
		$('.preloader').fadeOut('slow');
		$('html').removeClass('body-blocked');
		$('body').addClass('loaded');
	}, 1500);
});

const form = document.forms[0];
let fieldPassword = document.querySelector('#password');
let fieldConfirmPassword = document.querySelector('#userConfirmPassword');

form.addEventListener('submit', (e) => {

	validPassword();
	checkPasswordMatch();

	if(!validPassword() || !checkPasswordMatch()) {
		e.preventDefault();
	}
})

function setErrorFor(input, message) {
	let passwordError = input.parentElement.querySelector(".error-message");
	passwordError.innerHTML = message;
}

function setSuccessFor(input) {
	let passwordError = input.parentElement.querySelector(".error-message");
	passwordError.innerHTML = "";
}
function isPassword(input) {
	const passwordPattern = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	return passwordPattern.test(String(input));
}

function validPassword() {
	let fieldPasswordValue = fieldPassword.value.trim();

	if (fieldPasswordValue == "") {
		setErrorFor(fieldPassword, "Password cannot be blank");
	}
	else if (!isPassword(fieldPasswordValue)) {
		setErrorFor(fieldPassword, "Min 8 characters, one uppercase letter, one lowercase letter, one number and one special symbol");
	}
	else {
		setSuccessFor(fieldPassword);
		return true;
	}
	return true;
}
function checkPasswordMatch() {
	if (fieldConfirmPassword.value == "") {
		setErrorFor(fieldConfirmPassword, "Password cannot be blank");
	} else if (fieldConfirmPassword.value != fieldPassword.value) {
		setErrorFor(fieldConfirmPassword, "Passwords do not match!");
	} else if (!isPassword(fieldPassword.value)) {
		setErrorFor(fieldPassword, "Min 8 characters, one uppercase letter, one lowercase letter, one number and one special symbol");
	} else {
		setSuccessFor(fieldConfirmPassword);
		return true;
	}
	return false;
}
// Add Modal Income
$('#incomeOpen').click(function () {
	$('#incomeModal').fadeIn();
	$('html').addClass('body-blocked');
});
$('.income-modal__close').click(function () {
	$('#incomeModal').fadeOut();
	if ($('.hidden-nav').is(':visible')) {
	} else {
		$('html').removeClass('body-blocked');
	}
});


// Add Modal Expense
$('#expenseOpen').click(function () {
	$('#expenseModal').fadeIn();
	$('html').addClass('body-blocked');
});
$('.expense-modal__close').click(function () {
	$('#expenseModal').fadeOut();
	if ($('.hidden-nav').is(':visible')) {
	} else {
		$('html').removeClass('body-blocked');
	}
});


