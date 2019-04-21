$(function () {

    $.get('https://jsonplaceholder.typicode.com/users')
        .done(
            function (data) {
                $.each(data, function (key, value) {
                    $('#example').append(
                        '<tr id='+value.id+'><td>'+value.id+'</td><td>'+value.name+'</td><td>'+value.username+'</td><td>'+value.email+'</td></tr>'
                    );
                });
            }
        );

    let isShowed = true;
    $('#example').on('click', 'tr', function () {
        var self = $(this);
        if(isShowed){
            $.get('https://jsonplaceholder.typicode.com/posts?userId='+self.attr('id'))
                .done(
                    function (data) {
                        $(self).after('<table id="addedTable"></table>');
                        $.each(data, function (key, value) {
                            $('<tr><td>'+value.userId+'</td><td>'+value.id+'</td><td>'+value.title+'</td><td>'+value.body+'</td></tr>').appendTo("#addedTable");
                        });
                    }
                );
            isShowed = false;
        }else{
            $("#addedTable").detach();
            isShowed = true;
        }
    });
});

