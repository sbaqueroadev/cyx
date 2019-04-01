var path = require('path');
//var ExtractTextPlugin = require("extract-text-webpack-plugin");

module.exports = {
    devtool: 'sourcemaps',
    cache: true,
    debug: true,
    entry: {
        './js/app.js': path.resolve(__dirname, './src/main/js/app.js')
    },
    output: {
        path: path.resolve(__dirname, './src/main/resources/static/built/'),
        filename: '[name]'
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /(node_modules)/,
                loader: 'babel',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            },
            { 
                test: /\.css$/, 
                loader: "style-loader!css-loader" ,

            }/*,
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                  use: [
                    {
                      loader: 'css-loader',
                      options: {
                        url: false
                      }
                    }
                  ]
                })
            }*/
        ]
    },
    resolve: {
        modules: ['node_modules', 'src'],
          alias: {
            '~': path.join(__dirname, './node_modules/')
           }
      }
};