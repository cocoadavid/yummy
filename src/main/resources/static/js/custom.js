$(document).ready(function () {
    //--------------FOLLOW------------
    $('button#follow').click(function (event) {
        var username = $(event.currentTarget).data("username");
        $.ajax({
            url: '/follow/' + username,
            method: 'POST',
            error: function (err) {
                alert(err + ' ERROR');
            },
            success: function (data) {
                if ($("button#follow").attr("data-follow") === "follow") {
                    $("button#follow").attr("data-follow", "unfollow");
                    $("button#follow").text("Unfollow");
                    $("button#follow").css("backgroundColor", "#FF6666");
                    $("h5#counter").load(" h5#counter");
                }
                else if ($("button#follow").attr("data-follow") === "unfollow") {
                    $("button#follow").attr("data-follow", "follow");
                    $("button#follow").text("Follow");
                    $("button#follow").css("backgroundColor", "#2C3E50");
                }
            }
        })
    });
    //--------------LIKE------------
    $('button.yummy').click(function (event) {
        var button = $(event.currentTarget);
        var recipe_id = button.attr('id');
        $.ajax({
            url:'/yummy/' + recipe_id,
            method: 'POST',
            error: function () {
                alert('error');
            },
            success: function (data) {
                var info = JSON.parse(data);
                var yummy = info['yummy'];
                $('span#yummy' + recipe_id).text(yummy);
            }

        })
    })
//--------------SEARCH------------
    $("#searchButton").click(function (event) {
        event.preventDefault();
        var searchTerm = $('#searchTerm').val();
        $("#content").empty();
        $.ajax({
            url: "/search/" + searchTerm,
            method: 'GET',
            async: false,
            success: function(data) {
                var info = JSON.parse(data);
                var searchedUserList = info['searchedUsers'];
                var users = JSON.parse(searchedUserList);
                for( var i = 0; i < users.length; i++) {
                    if (users[i] == info['loggedInUser']) {
                        $("#content").append(
                            "<div class='col-md-6'>" +
                            "<ul class='list-group'>" +
                            "<li class='list-group-item'>" +
                            "<div class='col-xs-12 col-sm-3'>" +
                            "<a href='/myprofile/" + users[i] + "'>" +
                            "<img src='/images/profile/default_profile_picture.png' width='100%' alt='Yummy' />" +
                            "</a></div>" +
                            "<div class='col-xs-12 col-sm-9'>" +
                            "<a href='/myprofile/" + users[i] + "' style='margin-left: 15px; margin-top: 30px; color: #FF6666; float: left; font-size: x-large;'>" + users[i] + "</a>" +
                            "</div><div class='clearfix'></div></li></ul></div>"
                        );
                    }
                    else {
                        $("#content").append(
                            "<div class='col-md-6'>" +
                            "<ul class='list-group'>" +
                            "<li class='list-group-item'>" +
                            "<div class='col-xs-12 col-sm-3'>" +
                            "<a href='/profile/" + users[i] + "'>" +
                            "<img src='/images/profile/default_profile_picture.png' width='100%' alt='Yummy' />" +
                            "</a></div>" +
                            "<div class='col-xs-12 col-sm-9'>" +
                            "<a href='/profile/" + users[i] + "' style='margin-left: 15px; margin-top: 30px; color: #FF6666; float: left; font-size: x-large;'>" + users[i] + "</a>" +
                            "</div><div class='clearfix'></div></li></ul></div>"
                        );
                    }
                }
            },
            error: function (err) {
                alert('Error:' + err[0]);
            }
        });
    });

});