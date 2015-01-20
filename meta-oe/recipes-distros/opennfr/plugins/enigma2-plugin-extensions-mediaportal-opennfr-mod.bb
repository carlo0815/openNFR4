SUMMARY = "NFR Mediaportalmod GST1.0"
require conf/license/license-gplv2.inc
RDEPENDS_${PN} = "gstreamer1.0-plugins-bad-fragmented, gstreamer1.0-plugins-good-flv, gstreamer1.0-plugins-bad-rtmp"
PV = "1.0"
PR = "r0"
ALLOW_EMPTY_${PN} = "1"
