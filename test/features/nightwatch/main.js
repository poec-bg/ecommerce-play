module.exports = {
    'Test Home page': function(browser) {
        browser
            .url('http://localhost:9000')
            .waitForElementVisible('body', 1000)
            .assert.containsText('.login-link', 'Se connecter')
            .end();
    }
};