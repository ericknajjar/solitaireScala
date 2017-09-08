var merge = require("webpack-merge");
var webpack = require('webpack');
var optConfig = require("./opt.webpack.config.js");
var path = require('path');

var phaserModule = path.join(__dirname, '/node_modules/phaser/');
var phaser = path.join(phaserModule, 'build/custom/phaser-split.min.js')
var pixi = path.join(phaserModule, 'build/custom/pixi.min.js');
var p2 = path.join(phaserModule, 'build/custom/p2.min.js');

var config = require('./scalajs.webpack.config');

var phaserNames = ["pixi.js","p2","phaser"];

Object.keys(config.entry).forEach(function(key) {
  // Prepend each entry with the globally exposed JS dependencies
  config.entry[key] = phaserNames.concat(config.entry[key]);
});



var phaserConfig = {

    module: {
           loaders: [
                { test: /pixi.min.js/, loader: "expose-loader?PIXI" },
                { test: /phaser-split.min.js/, loader: 'expose-loader?Phaser'},
                { test: /p2.min.js/, loader: "expose-loader?p2"}
            ]
        },
        resolve: {
            alias: {
                'phaser': phaser,
                'pixi.js': pixi,
                'p2': p2,
            }
        }
};


module.exports = merge.smart(config,phaserConfig,optConfig);

