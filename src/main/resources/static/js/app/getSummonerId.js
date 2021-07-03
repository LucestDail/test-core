/**
 * 
 */

main = {
    init : function () {
    	console.log("main function activated");
        var _this = this;
        $('#btn-search').on('click', function () {
            _this.search();
        });
    },
    search : function () {
    	console.log("getSummonerId Search Function activated");
        var data = {
            id: $('#searchId').val(),
            region: $('#searchRegion').val()
        };
		console.log(data);
        $.ajax({
            type: 'POST',
            url: '/postSummonerId',
            dataType: "text",
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
        })
        .done(function() {
        	console.log("search success on console log");
            alert('search success on alert');
            location.reload();
        })
        .fail(function (error) {
        	console.log("search fail on console log");
        	console.log(JSON.stringify(error));
            alert(JSON.stringify(error));
        });
    }
};


main.init();
