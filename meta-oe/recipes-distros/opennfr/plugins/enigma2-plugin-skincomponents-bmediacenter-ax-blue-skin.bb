SUMMARY = "Skinpart for Full HD Skin for NFR Images by stein17 no Clone"
MAINTAINER = "opennfr"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
inherit allarch

require conf/license/license-gplv2.inc

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "2.5+git${SRCPV}"
PKGV = "2.5+git${GITPKGV}"
VER ="2.5"
PR = "r2"

SRC_URI="git://github.com/stein17/Skins-for-Plugins-by-stein17.git;protocol=https;branch=master"

S = "${WORKDIR}/git/BMediacenter-AX-Blue-Skin"

FILES:${PN} = "${libdir}"

do_install() {
    install -d ${D}${libdir}
    cp -rp ${S}/usr/lib/* ${D}${libdir}/
}

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
