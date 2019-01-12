<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="lien_assets" value="/site/assets"/>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Nopacks</title>

    <!-- Favicon -->
    <link rel="icon" href="${lien_assets}/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${lien_assets}/style.css">
</head>

<body style="overflow-x:hidden;">
    <!-- Preloader -->
    <div class="preloader d-flex align-items-center justify-content-center">
        <div class="lds-ellipsis">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>

       <jsp:include page="/WEB-INF/jsp/includables2/menu.jsp">
           <jsp:param name="lien_assets" value="${lien_assets}"/>
           </jsp:include>
    <!-- ##### Hero Area Start ##### -->
    <section class="hero-area">
      <div class="hero-slides owl-carousel owl-loaded owl-drag">
                  <!-- Single Hero Slide -->


                  <!-- Single Hero Slide -->

              <div class="owl-stage-outer"><div class="owl-stage" style="transform: transition: all 0s ease 0s; width: 8094px;">
                <div class="owl-item active" style="width: 1349px;"><div class="single-hero-slide d-flex align-items-center justify-content-center" style="padding-left:0;">
                      <!-- Slide Img -->
                      <div class="slide-img bg-img" style="background-image: url(${pageContext.servletContext.contextPath}/res/${hira.image});"></div>
                      <canvas id="analyser_render" width="1349px" height="650px" style="position:absolute; top:0px;left:0px;z-index:-2;height:100%;width:100%;"></canvas>

                      <!-- Slide Content -->
                      <img src="${pageContext.servletContext.contextPath}/res/${hira.image}" id="sary" style="display:none;" />
                      <div class="container" style="z-index:-1; margin-left:0px; max-width:100%;">
                          <div class="row">
                              <div class="col-12" style="padding-left:0;">
                                  <div class="hero-slides-content text-center">
                                      <h6 data-animation="fadeInUp" data-delay="100ms" style="animation-delay: 100ms;">${hira.auteur}</h6>
                                      <h2 data-animation="fadeInUp" data-delay="300ms" style="animation-delay: 300ms;">${hira.titre}<span>${hira.titre}</span></h2>
                                      <audio id="hirahira"  style="width:100%;" preload="true" src="${pageContext.servletContext.contextPath}/res/${hira.nomfichier}" controls></audio>
                                    </div>

                              </div>
                          </div>
                      </div>
                  </div>
                </div>
                </div>
    </section>
    <!-- ##### Hero Area End ##### -->

        <!-- ##### Latest Albums Area Start ##### -->
    <section class="latest-albums-area section-padding-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading style-2">
                        <h2>${pl.nom}</h2>
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-12 col-lg-9">
                    <div class="ablums-text text-center mb-70">
                        <c:if test="${!empty precedent}"><a href="${pageContext.servletContext.contextPath}/site/singlepl/${idsd}/${precedent}">Precedent</a></c:if><p>  Naviguer dans la playlist  </p><c:if test="${!empty suivant}"><a href="${pageContext.servletContext.contextPath}/site/singlepl/${idsd}/${suivant}"></a></c:if>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div class="albums-slideshow owl-carousel">
                        <!-- Single Album -->
                        <c:forEach items="${listeHira}" var="sg" varStatus="loop">
                        <div class="single-album">
                            <img src="${pageContext.servletContext.contextPath}/res/${sg.image}" alt="">
                            <div class="album-info">
                                <a href="${pageContext.servletContext.contextPath}/site/singlepl/${idsd}/${loop.index}">
                                    <h5>${sg.titre}</h5>
                                </a>
                                <p>${sg.auteur}</p>
                            </div>
                        </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Latest Albums Area End ##### -->

    
        <!-- ##### Footer Area Start ##### -->
    <footer class="footer-area">
        <div class="container">
            <div class="row d-flex flex-wrap align-items-center">
                <div class="col-12 col-md-6">
                    <a href="#"><img src="${lien_assets}/img/core-img/logo.png" alt=""></a>
                    <p class="copywrite-text"><a href="#"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                </div>

                <div class="col-12 col-md-6">
                    <div class="footer-nav">
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li><a href="#">Albums</a></li>
                            <li><a href="#">Events</a></li>
                            <li><a href="#">News</a></li>
                            <li><a href="#">Contact</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area Start ##### -->

    <!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="${lien_assets}/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="${lien_assets}/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${lien_assets}/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="${lien_assets}/js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="${lien_assets}/js/noactive.js"></script>

    <script src="${lien_assets}/js/Vibrant.js"></script>
    <script>
var ashta=0;
    function getPosition(element) {
        var xPosition = 0;
        var yPosition = 0;

        while(element) {
            xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
            yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
            element = element.offsetParent;
        }

        return { x: xPosition, y: yPosition };
    }


    var cl1="#FFFFFF";
    var crgb=[255,255,255];
    var img=document.getElementById("sary");
    console.log("tonga eto");
    $("#sary").one("load",function() {
      console.log("nididtra");
      if(img!=null){
    var vibrant = new Vibrant(img);
    var swatches = vibrant.swatches();
    for (var swatch in swatches){
        if (swatches.hasOwnProperty(swatch) && swatches[swatch]){
            console.log(swatch, swatches[swatch].getHex());
            if(swatch=="Vibrant" || swatch=="LightVibrant"){
              cl1=""+swatches[swatch].getHex();
              crgb=swatches[swatch].getRgb();
            }
          }
  }
      }
  console.log("loggin rgb");
  console.dir(crgb);

    /*
     * Results into:
     * Vibrant #7a4426
     * Muted #7b9eae
     * DarkVibrant #348945
     * DarkMuted #141414
     * LightVibrant #f3ccb4
     */
}).each(function() {
  if(this.complete) {
      $(this).load(); // For jQuery < 3.0
      // $(this).trigger('load'); // For jQuery >= 3.0
  }
});

var clientHeight = document.getElementById('header').clientHeight;
console.log(clientHeight);
var tpp=clientHeight+20;
//tpp=getPosition(document.getElementById("hirahira").offsetParent);
var audio = document.getElementById("hirahira");
//audio.src = 'track2.mp3';
audio.controls = true;
audio.autoplay = false;
<c:if test="${!empty suivant}">
    audio.onended = function() {
        alert("vita");
    window.location.href = "${pageContext.servletContext.contextPath}/site/singlepl/${idsd}/${suivant}";
};
</c:if>
window.addEventListener("load", initMp3Player, false);
function initMp3Player(){
  context = new AudioContext(); // AudioContext object instance
  analyser = context.createAnalyser(); // AnalyserNode method
  analyser.fftSize=16384;
  canvas = document.getElementById('analyser_render');

//  tpp=getPosition(document.getElementById("hirahira")).y+43;
tpp=getPosition($(".audioplayer")[0]).y;
  tpp=tpp*(canvas.height/canvas.clientHeight);
  console.log("new tpp ", tpp);
  ctx = canvas.getContext('2d');
console.log(canvas.clientHeight);
console.log("sy");
console.log(canvas.height);
  // Re-route audio playback into the processing graph of the AudioContext
  source = context.createMediaElementSource(audio);
  source.connect(analyser);
  analyser.connect(context.destination);
  analyser.smoothingTimeConstant=0.7;
  console.log(analyser.smoothingTimeConstant);
  console.log(canvas.height);
//  document.getElementById("hirahira").offsetParent.style.background="rgba(0,0,0,0)";
//  document.getElementById("hirahira").width=canvas.clientWidth;
console.log("roror");
$(".audioplayer").css("background-color", "rgba(255,255,255,0.4)");
$(".audioplayer").width($(window).width());
ashta=$(".audioplayer").height()*(canvas.height/canvas.clientHeight);
$(".audioplayer").css("border", "none");
console.log($(".audioplayer").width());
console.log($(window).width());
console.log("roror");
  frameLooper();
}
// frameLooper() animates any style of graphics you wish to the audio frequency
// Looping at the default frame rate that the browser provides(approx. 60 FPS)
function frameLooper(){
  window.requestAnimationFrame(frameLooper);
  fbc_array = new Uint8Array(analyser.frequencyBinCount);
  analyser.getByteFrequencyData(fbc_array);
  ctx.clearRect(0, 0, canvas.width, canvas.height); // Clear the canvas
  ctx.shadowColor = cl1;
  ctx.globalCompositeOperation = "lighter";
  ctx.shadowBlur = 5;
  bars = 120;
  avg=0;
  for (var i = 0; i < 120; i++) {
    avg+=fbc_array[i];
  }
  avg=avg/(120*400);
  //	console.log(avg);
  ctx.fillStyle = "rgba("+crgb[0]+", "+crgb[1]+", "+crgb[2]+", " + avg + ")";
  ctx.fillRect(0,canvas.height,canvas.width,-canvas.height,1.5);
  ctx.fillStyle = cl1; // Color of the bars
  bar_width = canvas.width/(2*bars);
  for (var i = 0; i < bars; i++) {
    bar_x = i *( bar_width * 2);
    bar_height = -(fbc_array[i] /3);

    //  fillRect( x, y, width, height ) // Explanation of the parameters below
    ctx.fillRect(bar_x, tpp+ashta, bar_width, -bar_height, 1.5);
    ctx.fillRect(bar_x, tpp, bar_width, bar_height, 1.5);
    //ctx.moveTo(bar_x,0);
    //ctx.lineTo(bar_x,bar_height);
    //ctx.stroke();
  }
}

$(window).resize(function(){
  $(".audioplayer").width($(window).width());
  $("#analyser_render").width($(window).width());

  ashta=$(".audioplayer").height()*(canvas.height/canvas.clientHeight);
  tpp=getPosition($(".audioplayer")[0]).y;
    tpp=tpp*(canvas.height/canvas.clientHeight);
  console.log(" rdd "+$("#analyser_render").width());
});




    </script>
</body>

</html>
