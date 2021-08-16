SUMMARY = "Full HD Skin for Anadol Boxes"
MAINTAINER = "stein17"

require conf/license/license-gplv2.inc

inherit gitpkgv allarch

SRCREV = "${AUTOREV}"
PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"
VER="1.0"

RDEPENDS:${PN} = "enigma2-plugin-skincomponents-weathercomponent, enigma2-plugin-systemplugins-weathercomponenthandler"

SRC_URI="git://github.com/stein17/Skins-for-openNFR.git;protocol=git"

FILES:${PN} = "/"



S = "${WORKDIR}/git/Anadol"


do_install() {
    install -d ${D}${libdir}
    install -d ${D}/usr/share
    cp -rp ${S}/usr/lib/* ${D}${libdir}/
    cp -rp ${S}/usr/share/* ${D}/usr/share/
    chmod -R a+rX ${D}/usr/share/enigma2/
}

pkg_postinst:${PN} () {
#!/bin/sh
echo "                                                          "
echo "    Anadol Skin Full HD by stein17 successful installed.  "
echo "                                                          "
exit 0
}

pkg_postrm:${PN} () {
#!/bin/sh
rm -rf /usr/share/enigma2/Anadol
rm -rf ${libdir}/enigma2/python/Components/Converter/AND*
rm -rf ${libdir}/enigma2/python/Components/Renderer/AND*
echo "                                                          "
echo "              ...Skin successful removed.                 "
echo "                                                          "
exit 0
}

pkg_preinst:${PN} () {
#!/bin/sh                                                       
rm -rf /usr/share/enigma2/Anadol
rm -rf ${libdir}/enigma2/python/Components/Converter/AND*
rm -rf ${libdir}/enigma2/python/Components/Renderer/AND*
echo "                                                                           "
echo "  Anadol Skin Full HD by stein17 is now being installed...                 "
echo "                                                                           "
exit 0
}

pkg_prerm:${PN} () {
#!/bin/sh
echo "                                                           "
echo " Anadol Skin Full HD by stein17 is now being removed...    "
echo "                                                           "
exit 0
}

do_package_qa[noexec] = "1"
