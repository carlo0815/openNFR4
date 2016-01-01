DESCRIPTION = "opera-hbbtv-browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

DEPENDS = "tslib mpfr gmp libcrypto0.9.8 ${@base_contains("GST_VERSION", "1.0", "gstreamer1.0", "gstreamer", d)}"
RDEPENDS_${PN} = "libsysfs2 libgmp10 libmpfr4 vuplus-opera-dumpait libcrypto0.9.8"

PACKAGES =+ "${PN}-src enigma2-hbbtv-util enigma2-hbbtv-util-src"
PROVIDES =+ "enigma2-hbbtv-util"

SRC_DATE = "20151001_1"
SRC_URI = ""

PR = "r42_${SRC_DATE}"

ALLOW_EMPTY_${PN} = "1"




