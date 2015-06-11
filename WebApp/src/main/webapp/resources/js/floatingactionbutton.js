$(document).ready(function() {
    $(".button-floating").click(function() {
        var $wrapper = $("#wrapper");

        if (!$wrapper.hasClass("button-floating-clicked"))
            $wrapper.toggleClass("button-floating-clicked-out");

        $wrapper.toggleClass("button-floating-clicked");
    });
});