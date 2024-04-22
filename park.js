var google_afd_request = {"client":"ca-dp-sedo89_3ph","drid":"as-drid-2244365169148097","domain_name":"npm-dependencies.com","session_token":"create"};

var setup = {
    domain: 'npm\u002Ddependencies.com',
    registrar: 'IONOSParkingDE',
    httpHost: 'sedoparking.com',
    customParams: '',
};

var contentContainer=document.getElementById("partner");function insertScript(url){var script=document.createElement("script");script.setAttribute("src",url);contentContainer.innerHTML="";contentContainer.insertAdjacentElement("afterEnd",script)}function google_afd_ad_request_done(google_afd_response){if(typeof google_afd_response.session_token=="undefined"){google_afd_response.session_token=""}loadContentFrame(google_afd_response.session_token)}function loadGoogleToken(){insertScript("//pagead2.googlesyndication.com/apps/domainpark/show_afd_ads.js")}function loadContentFrame(session_token){var contentFrame=document.createElement("iframe");contentFrame.setAttribute("src","//"+setup.httpHost+"/search/registrar.php"+"?domain="+setup.domain+"&rpv=2"+"&registrar="+setup.registrar+"&gst="+session_token+"&ref="+document.referrer+setup.customParams);contentFrame.setAttribute("name","regpark");contentFrame.setAttribute("frameBorder","0");contentContainer.appendChild(contentFrame)}

loadGoogleToken();
