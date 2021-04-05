DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
SRC_URI[md5sum] = "bfeccca75bdc3581bfc2d91abf0b3eca"
SRC_URI[sha256sum] = "82cec3bc475b3ae37ca7476773622b1e7da1497873624fe1af8495a25118f742"

PACKAGE_ARCH = "${MACHINEBUILD}"

PV = "19"

RDEPENDS_${PN} += "virtual/kodi kodi-addons-meta"

RRECOMMENDS_${PN} = "${@bb.utils.contains("MACHINE_FEATURES", "no-subssupport", "" , "enigma2-plugin-extensions-subssupport", d)}"

SRCREV = "491bf29f3810d1beef20484b7886bcd0724aabc6"
SRC_URI = "https://github.com/oe-alliance/kodiext.git;protocol=https;branch=python3 \
	file://advancedsettings.xml \
	"

S = "${WORKDIR}/git"

do_install_append() {
	install -d ${D}/usr/share/kodi/system
	install -m 0755 ${WORKDIR}/advancedsettings.xml ${D}/usr/share/kodi/system
}

FILES_${PN} = " \
	${libdir}/enigma2/python/Plugins/Extensions/Kodi \
	${bindir}/kodiext \
	/usr/share/kodi/system \
	"

inherit autotools
INSANE_SKIP += "file-deps"
