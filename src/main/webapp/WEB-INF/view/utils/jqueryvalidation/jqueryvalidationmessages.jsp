<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript">
jQuery.extend(jQuery.validator.messages, {
    required: "<spring:message code="crsms.area.empty"/>",
    remote: "Будь ласка, введіть правильне значення.",
    email: "Будь ласка, введіть коректну адресу електронної пошти.",
    url: "Будь ласка, введіть коректний URL.",
    date: "Будь ласка, введіть коректну дату.",
    dateISO: "Будь ласка, введіть коректну дату у форматі ISO.",
    number: "Будь ласка, введіть число.",
    digits: "Вводите потрібно лише цифри.",
    creditcard: "Будь ласка, введіть правильний номер кредитної карти.",
    equalTo: "Будь ласка, введіть таке ж значення ще раз.",
    accept: "Будь ласка, виберіть файл з правильним розширенням.",
    maxlength: "<spring:message code="crsms.area.long"/>",
    minlength: "<spring:message code="crsms.area.short"/>",
    rangelength: jQuery.validator.format("Будь ласка, введіть значення довжиною від {0} до {1} символів."),
    range: jQuery.validator.format("Будь ласка, введіть число від {0} до {1}."),
    max: jQuery.validator.format("Будь ласка, введіть число, менше або рівно {0}."),
    min: jQuery.validator.format("Будь ласка, введіть число, більше або рівно {0}.")
});
</script>