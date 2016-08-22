module.exports = {
    'Test Home page on mobile and desktop': function(browser) {
        browser
            .resizeWindow(360, 640)
            .url('http://localhost:9000')
            .waitForElementVisible('body', 1000)
            .assert.containsText('.login-link', 'Se connecter')
            .saveScreenshot( 'test/features/screenshots/test-home-mobile.png')
            .resizeWindow(992, 640)
            .saveScreenshot( 'test/features/screenshots/test-home-desktop.png')
            .end();
    }
};