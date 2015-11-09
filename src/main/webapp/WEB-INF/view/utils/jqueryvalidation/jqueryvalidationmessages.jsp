<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
jQuery.extend(jQuery.validator.messages, {
	    required: "<spring:message code="crsms.error.field.required"/>",
	    remote: "Будь ласка, введіть правильне значення.",
	    email: "Будь ласка, введіть коректну адресу електронної пошти.",
	    date: "<spring:message code="crsms.error.date.required"/>",
	    number: "Будь ласка, введіть число.",
	    digits: "Вводите потрібно лише цифри.",
	    equalTo: "Будь ласка, введіть таке ж значення ще раз.",
	    accept: "Будь ласка, виберіть файл з правильним розширенням.",
	    maxlength: "<spring:message code="crsms.error.field.too.long"/>",
	    minlength: "<spring:message code="crsms.error.too.short"/>",
	    rangelength: jQuery.validator.format("Будь ласка, введіть значення довжиною від {0} до {1} символів."),
	    range: jQuery.validator.format("Будь ласка, введіть число від {0} до {1}."),
	    max: jQuery.validator.format("Будь ласка, введіть число, менше або рівно {0}."),
	    min: jQuery.validator.format("Будь ласка, введіть число, більше або рівно {0}.")
});
</script>