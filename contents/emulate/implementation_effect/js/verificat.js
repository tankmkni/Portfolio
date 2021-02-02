/******************
 *  verificat.js  *
 ******************/

function verificat(){

  /*** 値を取得する関数リテラルの用意 ***/
  const GET_ELM = (id) => document.getElementById(id);
  const STYLE   = (id) => getComputedStyle( GET_ELM(id) );
  const POS_PRO = (id) => STYLE(id).getPropertyValue("position");
  const VIEW    = (id) => id
                       + " x:" + GET_ELM(id).offsetLeft
                       + " y:" + GET_ELM(id).offsetTop
                       + "&emsp;" + POS_PRO(id) + "<br/>";

  document.querySelector('.inner').onmousemove = ( ee ) => {

    /*** [object MouseEvent] から取得される値 ***/
    let page = "ee.page x:" + ee.pageX
                    + " y:" + ee.pageY + "<br/>";
    let target = "ee.target x:" + ee.target.offsetLeft
                        + " y:" + ee.target.offsetTop + "<br/>";

    /*** 値を取得して表示 ***/
    const INNER = GET_ELM("inner");
    INNER.innerHTML = page + target + "<br/>"
                    + VIEW("id1") + VIEW("id2")
                    + VIEW("border") + VIEW("notch") + VIEW("inner");
    INNER.style.setProperty("font-size","3.5vw");


    /*** ここから下は test1() と同じコード ***/
    const xx = ee.pageX - ee.target.offsetLeft
    const yy = ee.pageY - ee.target.offsetTop

    ee.target.style.setProperty('--x', `${ xx }px`)
    ee.target.style.setProperty('--y', `${ yy }px`)
  }
}