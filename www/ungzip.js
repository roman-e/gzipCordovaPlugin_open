var inflate = {
	inflateContent: function(deflatedContent, success, error){
		cordova.exec(
			success,
			error,
			"InflateService",
			"inflate",
			[{
				"content": deflatedContent
			}]
		);
	}
};

module.exports = inflate;