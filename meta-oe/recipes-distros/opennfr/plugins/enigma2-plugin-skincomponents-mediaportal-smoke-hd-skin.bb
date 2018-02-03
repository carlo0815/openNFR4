SUMMARY = "HD Skinpart for Mediaportal in openNFR by stein17 no Clone "
MAINTAINER = "stein17"
SECTION = "base"
PRIORITY = "required"
LICENSE = "proprietary"
inherit allarch

require conf/license/license-gplv2.inc

inherit gitpkgv
SRCREV = "${AUTOREV}"
PV = "2.0+git${SRCPV}"
PKGV = "2.0+git${GITPKGV}"
VER ="2.0"
PR = "r1"

SRC_URI="git://github.com/stein17/Mediaportal-Smoke-HD-Skin.git"

S = "${WORKDIR}/git"

FILES_${PN} = "/usr/*"

do_install() {
    cp -rp ${S}/usr ${D}/
}

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"
