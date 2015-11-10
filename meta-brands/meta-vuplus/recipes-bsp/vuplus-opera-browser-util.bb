DESCRIPTION = "opera-hbbtv-browser"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "CLOSED"

DEPENDS = "tslib mpfr gmp"
RDEPENDS_${PN} = "tslib-conf libts-1.0-0 libsysfs2 libgmp10 libmpfr4"

PACKAGES =+ "${PN}-src enigma2-hbbtv-util enigma2-hbbtv-util-src"
PROVIDES =+ "enigma2-hbbtv-util"

SRC_DATE = "20130820_0"
SRC_URI = ""

PR = "r24_${SRC_DATE}"

ALLOW_EMPTY_${PN} = "1"




