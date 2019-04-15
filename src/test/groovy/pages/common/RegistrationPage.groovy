package pages.common

import pages.base.BasePage

class RegistrationPage extends BasePage {

    static url = config.kundenbereichUrl + "/user/register.html"

    private static final FORM_TITLE = "Jetzt CHECK24 Kundenkonto anlegen und Vorteile nutzen!".toUpperCase()

    static content = {
        newAnmeldungBreadcrumb() { $('li a[href="/user/register.html"]') }
        registerFormContainer() { $('#c24-page-container-content') }
        registratonFormTitle() { registerFormContainer.find('.c24-kb-headline') }
        registrationForm() { registerFormContainer.find('#register') }
        contentList() { registrationForm.find('.c24-kb-content-list') }

        //===== Email block
        emailAddressBlock() { $('.c24-kb-form-row', 0) }
        emailField() { emailAddressBlock.find('input#email') }
        tooltipEmail() { emailAddressBlock.find('.c24-kb-help') }

        //===== Password block
        passwordBlock() { $('.c24-kb-form-row', 1) }
        passwordField() { passwordBlock.find('input#password') }
        tooltipPassword() { passwordBlock.find('.c24-kb-help') }
        passwordStrength() { passwordBlock.find('.password-strength-container') }
        passwordStrengthDecision(){$('.password-strength-container #indicator-text')}
        barContainer() { passwordStrength.find('.bar-container') }
        barWording() { passwordStrength.find('.wording') }
        textIndicator() { passwordStrength.find('#indicator-text') }

        //===== Repeat password block
        repeatPasswordBlock() { $('.c24-kb-form-row', 2) }
        passwordRepetitionField() { repeatPasswordBlock.find('input#passwordrepetition') }

        //======
        registrationButton() { registrationForm.find('button[name="register"]') }
        loginLink() { registrationForm.find('a[href="/user/login.html"]') }

        //===== GDPR block
        GDPRContainer() { $('#terms-update') }
        termsLink() { GDPRContainer.find('a[href="/terms.html"]') }
        datenschutzLink() { GDPRContainer.find('a[href="https://www.check24.de/popup/datenschutz-check24-gmbh/"]') }

    }

    static at = {
        registratonFormTitle.text().toUpperCase().contains(FORM_TITLE)
        browser.getCurrentUrl().toLowerCase().startsWith(url)
        registrationButton.isDisplayed()

        newAnmeldungBreadcrumb.isDisplayed() && contentList.isDisplayed()
        emailField.isDisplayed() && tooltipEmail.isDisplayed()
        passwordField.isDisplayed() && tooltipPassword.isDisplayed() && passwordStrength.isDisplayed()
        barContainer.isDisplayed() && barWording.isDisplayed() && textIndicator.isDisplayed()
        passwordRepetitionField.isDisplayed()
        GDPRContainer.isDisplayed() && termsLink.isDisplayed() && datenschutzLink.isDisplayed()
        loginLink().isDisplayed()
    }

    def enterLoginPasswordRepeatPassword() {
        def email = getUniqEmail()
        def password = "Check24,passWord_123456!"
        emailField.value(email)
        passwordField.value("kurz")
        assert passwordStrengthDecision.text().equals("Zu kurz")
        passwordField.value(password)
        assert passwordStrengthDecision.text().equals("Sehr stark")
        passwordRepetitionField.value(password)
    }

    def clickOnRegistrationButton() {
        registrationButton.click()
    }

}
