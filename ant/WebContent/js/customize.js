(function($) {
            var methods={
                attach: function () {
                    $.each($("*[value]"),function(i,n){
                        $(n).addClass("DefaultText");
                        $(n).val($(n).attr("value"));
                    });

                    $("*[value]").bind("focus", methods._onFieldFocus);
                    $("*[value]").bind("blur", methods._onFieldBlur);
                },
                _onFieldFocus:function(event){
                    var field = $(this);
                    if($(field).val()==$(field).attr("value")){
                        $(field).val("");
                        $(field).removeClass("DefaultText");
                    }
                },
                _onFieldBlur: function (event) {
                    var field = $(this);
                    if($(field).val()=="" || $(field).value==$(field).attr("value")){
                        $(field).addClass("DefaultText");
                        $(field).val($(field).attr("value"));
                    }
                }
             };

            methods.attach();
         })(jQuery); 