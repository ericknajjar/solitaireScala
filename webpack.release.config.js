var merge = require("webpack-merge");
var webpack = require('webpack');
var optConfig = require("./opt.webpack.config.js");
var common = require("./webpack.common.config.js");


module.exports = merge.smart(common,optConfig);

