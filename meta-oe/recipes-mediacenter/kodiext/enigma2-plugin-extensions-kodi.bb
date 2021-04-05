DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "Maroš Ondrášek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
SRC_URI[md5sum] = "b234ee4d69f5fce4486a80fdaf4a4263"
SRC_URI[sha256sum] = "91d3e8014ef3b2ccca3d6b21a9ccbe70db6dfd5093d3182f4774fe3e551aba47"

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
