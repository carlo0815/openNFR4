DESCRIPTION = "Enigma2 plugin to launch and play media from Kodi"
AUTHOR = "MaroÅ¡ OndrÃ¡Å¡ek <mx3ldev@gmail.com>"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r0"

DEPENDS += "enigma2 virtual/kodi"
RDEPENDS_${PN} += "virtual/kodi"

RRECOMMENDS_${PN} = "enigma2-plugin-extensions-subssupport"

SRCREV = "241a5aabf50aa21ed06c8180e854a65ebec47790"
SRC_URI = "git://github.com/mx3L/kodiext;protocol=git;branch=master \
        file://0001-add-subtitleSelection-option.patch \
        file://kodiext_FHD.png \
        file://kodiext_HD \		
        "

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir}/enigma2/python/Plugins/Extensions/Kodi \
    ${bindir}/kodiext"

inherit autotools

do_install_append() {
	install -m 644 ${S}/kodiext_FHD.png ${libdir}/enigma2/python/Plugins/Extensions/Kodi/kodiext_FHD.png	
	install -m 644 ${S}/kodiext_HD.png ${libdir}/enigma2/python/Plugins/Extensions/Kodi/kodiext_HD.png
} 
