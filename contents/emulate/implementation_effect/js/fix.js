/************
 *  fix.js  *
 ************/

function fix(){
  document.querySelector('.inner').onmousemove = ( ee ) => {

    /*** ウィンドウの左上基準の値を取得して補正 ***/
    const elmBound = () => ee.target.getBoundingClientRect();
    const xx = ee.pageX - elmBound().left;
    const yy = ee.pageY - elmBound().top;

    ee.target.style.setProperty('--x', `${ xx }px`);
    ee.target.style.setProperty('--y', `${ yy }px`);
  }
}