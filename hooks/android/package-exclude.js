var fs = require("fs");
var path = require("path");

module.exports = function (context) {
    var gradleFile = path.join(
        context.opts.projectRoot,
        "platforms",
        "android",
        "build.gradle"
    );

    // Read the build.gradle file
    fs.readFile(gradleFile, "utf8", function (err, data) {
        if (err) {
            throw new Error("Unable to read build.gradle: " + err);
        }

        // Modify the file content to exclude the unwanted dependency
        var modifiedData = data.replace(
            /compile\(name:'barcodescanner-release-2\.1\.5', ext:'aar'\)/g,
            "compile(name:'barcodescanner-release-2.1.5', ext:'aar') {\n        exclude group: 'com.google.zxing.client.android'\n    }"
        );

        // Write the modified content back to the build.gradle file
        fs.writeFile(gradleFile, modifiedData, "utf8", function (err) {
            if (err) {
                throw new Error("Unable to write build.gradle: " + err);
            }
        });
    });
};
