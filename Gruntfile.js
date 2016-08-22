module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),

        concat: {
            css: {
                src: [
                    'public/stylesheets/main.css'
                ],
                dest: 'public/stylesheets/stylesheets.min.css'
            },
            cssLib:{
                src: [
                    'public/lib/bootstrap/3.3.7/css/bootstrap.min.css'
                ],
                dest: 'public/lib/lib.min.css'
            },
            js : {
                src: [
                    'public/javascripts/main.js', 'public/javascripts/ripple-effect.js'
                ],
                dest: 'public/javascripts/javascripts.min.js'
            },
            jsLib : {
                src: [
                    'public/lib/bootstrap/3.3.7/bootstrap.min.js'
                ],
                dest: 'public/lib/lib.min.js'
            }
        },

        cssmin : {
            css:{
                src: 'public/stylesheets/stylesheets.min.css',
                dest: 'public/stylesheets/stylesheets.min.css'
            }
        },

        uglify : {
            js: {
                files: {
                    'public/javascripts/javascripts.min.js' : [ 'public/javascripts/javascripts.min.js' ]
                }
            }
        },

        watch: {
            stylus:{
                files: ['public/stylesheets/*.styl'],
                tasks: ['stylus'],
                "options": {
                    "spawn": "false"
                }
            },
            browserify:{
                files: ['public/javascripts/es6/*.es6'],
                tasks: ['browserify']
            }
        },

        stylus: {
            compile: {
                options: {
                    paths: ['public/stylesheets'],
                    import: [
                        'typographic', 'mixins'
                    ],
                    compress: false,
                    use: [
                        require('rupture')
                    ]
                },
                files: {
                    'public/stylesheets/main.css': ['public/stylesheets/main.styl']
                }
            }
        },

        browserSync: {
            dev: {
                bsFiles: {
                    src: [
                        'public/stylesheets/*.css',
                        'public/javascripts/*.js',
                        'app/views/*.html'
                    ]
                },
                options: {
                    watchTask: true,
                    proxy: "localhost:9000",
                    port:9001,
                    open: false,
                    watchTask: true,
                    ghostMode: {
                        clicks: true,
                        location: true,
                        forms: true,
                        scroll: true
                    }
                }
            }
        },

        browserify: {
            development: {
                src: [
                    "public/javascripts/es6/*.es6"
                ],
                dest: 'public/javascripts/intro.js',
                options: {
                    browserifyOptions: { debug: true },
                    transform: [["babelify", { "presets": ["es2015", "stage-3"] , "plugins": [ "transform-runtime"]}]]
                }
            }
        },

        nightwatch: {
            options: {
                // task options
                standalone: true,

                // download settings
                jar_version: '2.53.1',
                jar_path: 'test/selenium/selenium-server-standalone-2.53.1.jar',

                // nightwatch settings
                globals: { foo: 'bar' },
                src_folders: ['test/features/nightwatch'],
                output_folder: 'test/features/report',

                // YOU HAVE TO DOWNLOAD THE DRIVERS => http://www.seleniumhq.org/download/
                test_settings: {
                    firefox: {
                        "desiredCapabilities": {
                            "browserName": "firefox"
                        }
                    },
                    phantom: {
                        "desiredCapabilities": {
                            "browserName": "phantomjs",
                            "phantomjs.binary.path": "test/selenium/phantomjs/windows/phantomjs.exe"
                        }
                    },
                    phantomMAC: {
                        "desiredCapabilities": {
                            "browserName": "phantomjs",
                            "phantomjs.binary.path": "test/selenium/phantomjs/mac/phantomjs"
                        }
                    },
                    phantomLinux: {
                        "desiredCapabilities": {
                            "browserName": "phantomjs",
                            "phantomjs.binary.path": "test/selenium/phantomjs/linux/phantomjs"
                        }
                    },
                    chrome: {
                        "desiredCapabilities": {
                            "browserName": "chrome"
                        },
                        "cli_args" : {
                            "webdriver.chrome.driver" : "test/selenium/chromedriver/windows/chromedriver.exe"
                        }
                    },
                    chromeMAC: {
                        "desiredCapabilities": {
                            "browserName": "chrome"
                        },
                        "cli_args" : {
                            "webdriver.chrome.driver" : "test/selenium/chromedriver/mac/chromedriver"
                        }
                    },
                    chromelinux: {
                        "desiredCapabilities": {
                            "browserName": "chrome"
                        },
                        "cli_args" : {
                            "webdriver.chrome.driver" : "test/selenium/chromedriver/linux/chromedriver"
                        }
                    }
                },
                selenium: {}
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-stylus');
    grunt.loadNpmTasks('grunt-browser-sync');
    grunt.loadNpmTasks('grunt-browserify');
    grunt.loadNpmTasks('grunt-selenium-server');
    grunt.loadNpmTasks('grunt-nightwatch');

    grunt.registerTask('default', [ 'concat:cssLib', 'concat:jsLib', 'concat:css', 'cssmin:css', 'concat:js', 'uglify:js']);
    grunt.registerTask('dev', [ 'watch']);
    grunt.registerTask('test-responsive', [ 'browserSync', 'watch']);
    grunt.registerTask('test', ['nightwatch']);
};