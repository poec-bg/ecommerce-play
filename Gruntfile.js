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
                    'public/javascripts/main.js'
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
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-stylus');
    grunt.loadNpmTasks('grunt-browser-sync');
    grunt.loadNpmTasks('grunt-browserify');

    grunt.registerTask('default', [ 'concat:cssLib', 'concat:jsLib', 'concat:css', 'cssmin:css', 'concat:js', 'uglify:js']);
    grunt.registerTask('dev', [ 'watch']);
    grunt.registerTask('test-responsive', [ 'browserSync', 'watch']);
};