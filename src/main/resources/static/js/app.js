$.cnnwebapp = {};

//Managing User in /userList
$.cnnwebapp.userManager = {
    
    getUserList: function () {
        var url = "api/user-manager/user-list";
        var tableBody = $("tbody");
        var userPosition= $('#userPosition').text();
        tableBody.html('');
        $.ajax({
            method: "GET",
            contentType: "application/json",
            dataType: 'json',
            url: url
        }).done(function (res) {
            $.each(res, function (index, userInfo) {
                var html = '<tr class="gradeA odd" role="row">' +
                        '<td class="sorting_1">' + userInfo.userName + '</td>' +
                        '<td>' + userInfo.role.toUpperCase() + '</td>' +
                        '<td>' + userInfo.department + '</td>';
                //Allowing only Professors to EDIT/DELETE users
                if (userPosition == "Professor") {
                    html +='<td>' + String(userInfo.createdAt) + '</td>' +
                        '<td class="center"><a class="btn btn-sm btn-primary m-t-n-xs" type="submit" href="/edit-user?id=' + userInfo.id + '"><strong>Edit</strong></a></td>' +
                        '<td class="center"><button class="btn btn-sm btn-primary m-t-n-xs" type="submit" onclick="$.cnnwebapp.userManager.deleteUser(\'' + userInfo.id + '\')"><strong>Delete</strong></button></td>';
                }
                html += '</tr>';
                tableBody.append(html);
            });
        });
    },
    
    editUserInit: function (userId) {
        $.cnnwebapp.userManager.getPositionList();
        //Deciding adding OR editing user
        if (userId == -1) {
            //add user
            $('#edit-user-save').attr('onclick', '$.cnnwebapp.userManager.addUser()');
        } else {
            //edit user
            $('#edit-user-save').attr('onclick', '$.cnnwebapp.userManager.editUser(' + userId + ')');
            $.ajax({
                method: "GET",
                url: "/api/user-manager/get-user/" + userId
            }).done(function (res) {
                //Returning the current data of user => For rendering on client side
                $('#username-input').attr('value', res.userName);
                $("option[value='" + res.roleId + "']").attr('selected','selected');
                $('#email-input').attr('value', res.email);
                $('#department-input').attr('value', res.department);
            });
        }
    },

    //Get all role from database --> Render as
    getPositionList: function () {
        var roleSelector = $('#position-selector');
        $.ajax({
            method: "GET",
            url: "/api/user/role-list"
        }).done(function (res) {
            $.each(res, function (index, roleObject) {
                var html = '<option value="' + roleObject.id + '">' + roleObject.roleName.toUpperCase() + '</option>';
                roleSelector.append(html);
            });
        });
    },

    addUser: function () {
        var roleId;

        if ($('#position-selector').children('option:selected').val() == "PROF") {
            roleId = 2
        } else roleId = 1

        var body = {
            name: $('#name-input').val(),
            userName: $('#username-input').val(),
            password: $('#password-input').val(),
            email: $('#email-input').val(),
            roleId:  roleId,
            department: $('#department-input').val()
        }
        var url = "/api/user-manager/add-user";
        $.ajax({
            method: "POST",
            url: url,
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify(body),
        }).done(function (res) {
            window.location.href = "/user-list";
        });
    },

    editUser: function (userId) {
        var roleId;
        if ($('#position-selector').children('option:selected').val() == "PROF") {
            roleId = 2
        } else roleId = 1
        //Fields collected from client side
        var body = {
                id: userId,
                userName: $('#username-input').val(),
                roleId: roleId,
                email: $('#email-input').val(),
                department: $('#department-input').val()
        }
        $.ajax({
            method: "PUT",
            url: "/api/user-manager/edit-user",
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify(body)
        }).done(function (res) {
            //Redirect to userLis after the request is executed
            window.location.href = "/user-list";
        });
    },

    changePassword: function () {
        var url = "/api/user-manager/change-password";

        var body = {
            userName: $('#username-input').val(),
            password: $('#password-input').val()
        }

        $.ajax({
            method: "PUT",
            url: url,
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify(body)
        }).success(function (res) {
            window.location.href = "/";
        }).error(function() {
            alert("Error !!")
        });
    },

    deleteUser: function (userId) {
        $.ajax({
            method: "DELETE",
            url: "/api/user-manager/delete-user/" + userId,
        }).done(function (res) {
            //Redload userlist
            $.cnnwebapp.userManager.getUserList();
        });
    }
};
