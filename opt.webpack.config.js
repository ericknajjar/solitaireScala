var CompressionPlugin = require("compression-webpack-plugin");
var ClosureCompilerPlugin = require('webpack-closure-compiler');
var webpack = require('webpack');

var gzipCompression = new CompressionPlugin({
			asset: "[path].gz[query]",
			algorithm: "gzip",
			test: /\.(js|html)$/,
			threshold: 10240,
			minRatio: 0.8
		});


var uglyfy = new webpack.optimize.UglifyJsPlugin();
var clojure = new ClosureCompilerPlugin();
module.exports = {

    plugins: [gzipCompression,uglyfy]

}