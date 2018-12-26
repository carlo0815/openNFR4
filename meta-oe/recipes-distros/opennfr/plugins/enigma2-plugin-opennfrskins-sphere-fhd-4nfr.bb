SUMMARY = "sphere-fhd Skin for NFR Images"
MAINTAINER = "opennfr"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"

require conf/license/license-gplv2.inc

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "2.1+git${SRCPV}"
PKGV = "2.1+git${GITPKGV}"
VER ="2.1"
PR = "r2"


SRC_URI="git://github.com/carlo0815/skins-sphere-fhd.git"

S = "${WORKDIR}/git"

FILES_${PN} = "${libdir} /usr/share"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}/usr/share
    cp -rp ${S}/usr/lib/* ${D}${libdir}/
    cp -rp ${S}/usr/share/* ${D}/usr/share/
}


do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
