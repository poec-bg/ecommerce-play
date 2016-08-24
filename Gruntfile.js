module.exports = function(grunt) {

// 1. Toutes les configurations vont ici:
grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),

    concat: {
        // 2. la configuration pour la concaténation va ici.
        dist: {
                src: [
                    'public/javascripts/inti.js',
                    'public/javascripts/inti-responsive.js',
                    'public/javascripts/signature.js',
                ],
                dest: 'public/javascripts/production.js'
            }
    },

    uglify: {
        build: {
            src: 'public/javascripts/production.js',
            dest: 'public/javascripts/production.min.js'
        }
    },
    // Configuration to be run (and then tested).
    stylus: {
        compile: {
             options: {
                paths:['public/stylesheets/'],
                import:[],
                compress: false
            },
            files: {
                'public/stylesheets/main.css': ['public/stylesheets/main.styl'],
                'public/stylesheets/detailProduit.css': ['public/stylesheets/detailProduit.styl']
            }
        }
    },
    watch: {
        files: [
            'public/stylesheets/*.styl',
        ],
        tasks: ['stylus'],
    }
});

// 3. Nous disons à Grunt que nous voulons utiliser ce plug-in.
grunt.loadNpmTasks('grunt-contrib-concat');
grunt.loadNpmTasks('grunt-contrib-uglify');
grunt.loadNpmTasks('grunt-contrib-stylus');
grunt.loadNpmTasks('grunt-contrib-watch');

// 4. Nous disons à Grunt quoi faire lorsque nous tapons "grunt" dans la console.
grunt.registerTask('default', ['concat', 'uglify','stylus']);

};