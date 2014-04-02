(function($){
    $("#user_name").on("focus",function(){
        $(".login-handle").animate({
            top : 150
        });
    });

    $(".btn-switch").on("click",function(){
    	$(this).toggleClass("switch-on");
        $("#auto_login").val( $("#auto_login").val() == "true" ? "false" : "true" );
    });

    $("#user_name").on("blur",function(){
        if( /^\w+$/.test(this.value) ){
            $(this).next().removeClass("error").addClass("correct");
            return true;
        }else{
            $(this).next().removeClass("correct").addClass("error");
            return false;
        }
    });
    $("#user_password").on("blur",function(){
        if( this.value.length >= 6 && this.value.length <= 20 ){
            $(this).next().removeClass("error").addClass("correct");
            return true;
        }else{
            $(this).next().removeClass("correct").addClass("error");
            return false;
        }
    });

    $("#login-form").on("submit",function(){
        return !$(this).find(".tip").hasClass("error");
    });

})(jQuery);
