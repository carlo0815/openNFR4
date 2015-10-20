SUMMARY = "KravenVB Skin for Enigma2 by Kraven"
MAINTAINER = "Kraven"

require conf/license/license-gplv2.inc

inherit gitpkgv allarch

SRCREV = "${AUTOREV}"
PV = "2.4+git${SRCPV}"
PKGV = "2.4+git${GITPKGV}"
VER="2.4"

SRC_URI="git://github.com/KravenHD/KravenVB.git;protocol=git"

FILES_${PN} = "/usr/*"

S = "${WORKDIR}/git"

do_compile_append() {
python -O -m compileall ${S}
}

do_install() {
    install -d ${D}/usr/share/enigma2
    cp -rp ${S}/usr ${D}/
    chmod -R a+rX ${D}/usr/share/enigma2/
}

pkg_postinst_${PN} () {
#!/bin/sh
echo "                                                          "
echo "             ...Skin successful installed.                "
echo "                                                          "
wget -O /dev/null 'http://127.0.0.1/web/message?type=1&text=KravenVB%20wurde%20erfolgreich%20installiert.%0A%0AZur%20Nutzung%20rufen%20Sie%20das%20Plugin%20auf,%20speichern%20Ihre%20Einstellungen%20und%20starten%20die%20Oberfl%C3%A4che%20neu.&timeout=13'
exit 0
}

pkg_postrm_${PN} () {
#!/bin/sh
rm -rf /usr/share/enigma2/KravenVB
rm -rf /usr/lib/enigma2/python/Plugins/Extensions/KravenVB
rm -rf /usr/lib/enigma2/python/Components/Converter/KravenVB*
rm -rf /usr/lib/enigma2/python/Components/Renderer/KravenVB*
echo "                                                          "
echo "              ...Skin successful removed.                 "
echo "  
}

pkg_preinst_${PN} () {
#!/bin/sh
echo "Checking for previous installations..."
if opkg list-installed |grep enigma2-plugin-skins-kravenvb; then
		opkg remove enigma2-plugin-skins-kravenvb
		echo "                                                           "
		echo "            Previous KravenVB installation                 "
		echo "                 was found and removed!                    "
		echo "                                                           "
fi

if [ -f /usr/share/enigma2/KravenVB/skin.xml ]; then
    rm -rf /usr/share/enigma2/KravenVB
    rm -rf /usr/lib/enigma2/python/Components/Converter/KravenVB*
    rm -rf /usr/lib/enigma2/python/Components/Renderer/KravenVB*
		echo "                                                           "
		echo "             Previous KravenVB installation                "
		echo "                 was found and removed!                    "
		echo "                                                           "
fi
if [ -f /usr/lib/enigma2/python/Plugins/Extensions/KravenVB/plugin.py ]; then
    rm -rf /usr/lib/enigma2/python/Plugins/Extensions/KravenVB
		echo "                                                           "
		echo "              KravenVB configuration plugin                "
		echo "                 was found and removed!                    "
		echo "                                                           "
fi




echo "                                                           "
echo "        The Skin KravenVB is now being installed...        "
echo "                                                           "
exit 0
}

pkg_prerm_${PN} () {
#!/bin/sh
echo "                                                           "
echo "        The Skin KravenVB is now being removed...           "
echo "  
} 
