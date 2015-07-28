SUMMARY = "lightweight DLNA/UPnP-AV server targeted at embedded systems"
HOMEPAGE = "http://sourceforge.net/projects/minidlna/"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b1a795ac1a06805cf8fd74920bc46b5c"
DEPENDS = "libexif jpeg libid3tag flac libvorbis sqlite3 libav util-linux"

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
PR = "r1"

# NLS causes autoconfigure problems - we don't need the extra languages anyway, so disable nls
EXTRA_OECONF_append = " --disable-nls "

SRC_URI = "git://github.com/carlo0815/minidlna.git;protocol=git \
        file://minidlna.conf \
        file://minidlnad \
        file://init"

S = "${WORKDIR}/git"

inherit autotools-brokensep gettext update-rc.d

PACKAGES =+ "${PN}-utils"

FILES_${PN}-utils = "${bindir}/test*"

CONFFILES_${PN} = "${sysconfdir}/minidlna.conf"

INITSCRIPT_NAME = "minidlna"
INITSCRIPT_PARAMS = "defaults 20"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 644 ${WORKDIR}/minidlna.conf ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/init.d/
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/${PN}
    install -d ${D}/usr/sbin
    install -m 755 ${WORKDIR}/minidlnad ${D}/usr/sbin
}

do_configure_prepend() {
    touch ${S}/ABOUT-NLS
} 
