$(function() {
    $("form[name='registration']").validate({
        rules: {
            fname: "required",
            lname: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        messages: {
            fname: "Please enter your first name",
            lname: "Please enter your last name",
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 8 characters long"
            },
            email: "Please enter a valid email address"
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='login']").validate({
        rules: {
            fname: "required",
            lname: "required",
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 8
            }
        },
        messages: {
            fname: "Please enter your first name",
            lname: "Please enter your last name",
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 8 characters long"
            },
            email: "Please enter a valid email address"
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='add-exposition']").validate({
        rules: {
            topic: {
                required: true
            },
            description: {
                required: true,
                maxlength: 1024
            },
            price: {
                required: true,
                number: true
            },
            start_time: {
                required: true
            },
            end_time: {
                required: true
            },
            start_date: {
                required: true
            },
            end_date: {
                required: true
            }
        },
        messages: {
            topic: "Please enter topic",
            description: {
                required: "Please provide a description",
                maxlength: "Description must be less than 1024 characters long"
            },
            email: "Please enter a valid email address",
            price: {
                required: "Please provide a price",
                number: "Price must be a number"
            },
            start_time: {
                required: "Please provide business hours"
            },
            end_time: {
                required: "Please provide business hours"
            },
            start_date: {
                required: "Please provide working gap"
            },
            end_date: {
                required: "Please provide working gap"
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

    $("form[name='update-exposition']").validate({
        rules: {
            topic: {
                required: true
            },
            description: {
                required: true,
                maxlength: 1024
            },
            price: {
                required: true,
                number: true
            },
            start_time: {
                required: true
            },
            end_time: {
                required: true
            },
            start_date: {
                required: true
            },
            end_date: {
                required: true
            }
        },
        messages: {
            topic: "Please enter topic",
            description: {
                required: "Please provide a description",
                maxlength: "Description must be less than 1024 characters long"
            },
            email: "Please enter a valid email address",
            price: {
                required: "Please provide a price",
                number: "Price must be a number"
            },
            start_time: {
                required: "Please provide business hours"
            },
            end_time: {
                required: "Please provide business hours"
            },
            start_date: {
                required: "Please provide working gap"
            },
            end_date: {
                required: "Please provide working gap"
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});
