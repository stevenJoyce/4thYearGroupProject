(function(window, undefined) {
  var dictionary = {
    "abdc43e8-b1cb-4436-a4ae-524e2460d3a8": "Drop Down Menu",
    "24b25c6b-785b-4de3-a635-7aed43730986": "Nearby Golf Courses page",
    "6c3c8fc1-447b-4383-9310-0b272b10aba5": "Guest Home Page",
    "d12245cc-1680-458d-89dd-4f0d7fb22724": "Welcome Page",
    "db8fef49-ca00-4354-a7c9-c378277b6d48": "Home Page",
    "1c01cb94-d1dd-4ba9-93ee-0f7bfcbac4ae": "Reports Page",
    "ddd09d44-1c3a-418b-8427-567f505a6240": "Goals Page",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "Template 1",
    "bb8abf58-f55e-472d-af05-a7d1bb0cc014": "default"
  };

  var uriRE = /^(\/#)?(screens|templates|masters|scenarios)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);