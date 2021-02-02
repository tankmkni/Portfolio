/***************
 *  effect.js  *
 ***************/

  //alert("test : "+document.querySelector('#gradientEffect').onmousemove);
//document.querySelector('#gradientEffect').onmousemove = ( e ) => {
document.getElementById('#gradientEffect').onmousemove = ( e ) => {
  //alert("test");

  //const outer = document.getElementsByClassName("outer")[0]
  const outer = document.getElementById("borderGradient")
  const effectX = e.pageX - outer.offsetLeft
  const effectY = e.pageY - outer.offsetTop

  e.target.style.setProperty('--x', `${ effectX }px`)
  e.target.style.setProperty('--y', `${ effectY }px`)
}