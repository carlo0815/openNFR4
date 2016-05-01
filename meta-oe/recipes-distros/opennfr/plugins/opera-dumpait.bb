SUMMARY = "dumpait"
PRIORITY = "required"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.GPLv3;md5=5ed852a46d22220a8b07a68e564d84c7"

inherit autotools-brokensep pkgconfig gitpkgv

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"
PR = "r11"

PACKAGES += " ${PN}-src"

DEPENDS = "libdvbsi++"

SRC_URI = "git://github.com/carlo0815/opera-dumpait.git;protocol=http"

S = "${WORKDIR}/git"
DESTDIR = "enigma2/python/Plugins/Extensions/HbbTV"

do_install() {
    if [ "${BRAND_OEM}" = "vuplus" ]; then
        echo "use original"
    else    
        install -d ${D}/usr/lib/${DESTDIR}
        install -m 0755 ${S}/src/dumpait ${D}/usr/lib/${DESTDIR}
    fi    
}

FILES_${PN} = "${libdir}/${DESTDIR}/dumpait"
FILES_${PN}-dbg = "${libdir}/${DESTDIR}/.debug"
FILES_${PN}-src = "/usr/src"
