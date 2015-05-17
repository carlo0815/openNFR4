DESCRIPTION = "OpenNFR bootlogo"
SECTION = "base"
PRIORITY = "required"
MAINTAINER = "opennfr"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/license/license-gplv2.inc

RDEPENDS_${PN} += "showiframe"

PV = "4.2"
PR = "r2"

S = "${WORKDIR}"

INITSCRIPT_NAME = "bootlogo"
INITSCRIPT_PARAMS = "start 07 S ."
INITSCRIPT_PARAMS_vuduo2 = "start 70 S . stop 89 0 ."
INITSCRIPT_PARAMS_vusolo2 = "start 70 S . stop 89 0 ."
INITSCRIPT_PARAMS_vusolose = "start 70 S . stop 89 0 ."

inherit update-rc.d

SRC_URI = "file://bootlogo.mvi file://backdrop.mvi file://bootlogo_wait.mvi file://radio.mvi file://bootlogo.sh ${@base_contains("MACHINE_FEATURES", "bootsplash", "file://splash.bin" , "", d)} ${@base_contains("MACHINE_FEATURES", "bootsplash", "file://splash480.bin" , "", d)}"
SRC_URI_append_gb800ue = "file://lcdsplash.bin file://lcdwaitkey.bin file://lcdwarning.bin"
SRC_URI_append_gbquad = "file://lcdsplash.bin file://lcdwaitkey.bin file://lcdwarning.bin"
SRC_URI_append_gb800ueplus = "file://lcdsplash.bin file://lcdwaitkey.bin file://lcdwarning.bin"
SRC_URI_append_gbquadplus = "file://lcdsplash400.bin file://lcdwaitkey400.bin file://lcdwarning400.bin"
SRC_URI_append_vuduo2 = "file://lcdbootlogo.png file://bootlogo.py"

BINARY_VERSION = "1.3"


FILES_${PN} = "/boot /usr/share /etc/init.d"

do_install() {
    install -d ${D}/usr/share
    install -m 0644 bootlogo.mvi ${D}/usr/share/bootlogo.mvi
    ln -sf /usr/share/bootlogo.mvi ${D}/usr/share/backdrop.mvi
    install -d ${D}/usr/share/enigma2
    install -m 0644 radio.mvi ${D}/usr/share/enigma2/radio.mvi
    install -d ${D}/${sysconfdir}/init.d
    install -m 0755 bootlogo.sh ${D}/${sysconfdir}/init.d/bootlogo
}
do_install_append_gb800ue() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning.bin ${D}/usr/share/lcdwarning.bin
}

do_install_append_gbquad() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning.bin ${D}/usr/share/lcdwarning.bin	
}

do_install_append_gb800ueplus() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning.bin ${D}/usr/share/lcdwarning.bin	
}

do_install_append_gbquadplus() {
    install -d ${D}/usr/share
    install -m 0644 lcdwaitkey400.bin ${D}/usr/share/lcdwaitkey.bin
    install -m 0644 lcdwarning400.bin ${D}/usr/share/lcdwarning.bin
}

do_install_append_vuduo2() {
    install -m 0644 lcdbootlogo.png ${D}/usr/share/lcdbootlogo.png
    install -m 0644 bootlogo.py ${D}/${sysconfdir}/init.d/bootlogo.py
}

inherit deploy
do_deploy() {
    if [ "${BRAND_OEM}" = "vuplus" ] || [ "${BRAND_OEM}" = "dags" ]; then
	install -m 0644 splash480.bin ${DEPLOYDIR}/${BOOTLOGO_FILENAME}
    else
    	if [ -e splash.bin ]; then
        	install -m 0644 splash.bin ${DEPLOYDIR}/${BOOTLOGO_FILENAME}
    	fi
    fi
    if [ -e lcdsplash.bin ]; then
    install -m 0644 lcdsplash.bin ${DEPLOYDIR}/lcdsplash.bin
    fi
    if [ -e lcdsplash400.bin ]; then
        install -m 0644 lcdsplash400.bin ${DEPLOYDIR}/lcdsplash.bin
    fi

}

addtask deploy before do_build after do_install

pkg_preinst_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            if mountpoint -q /boot
            then
                mount -o remount,rw,compr=none /boot
            else
                mount -t jffs2 -o rw,compr=none mtd:'boot partition' /boot
            fi
        fi
    fi
}

pkg_postinst_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            umount /boot
        fi
    fi
}

pkg_prerm_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            if mountpoint -q /boot
            then
                mount -o remount,rw,compr=none /boot
            else
                mount -t jffs2 -o rw,compr=none mtd:'boot partition' /boot
            fi
        fi
    fi
}

pkg_postrm_${PN}() {
    if grep dm /etc/hostname > /dev/null ; then
        if [ -z "$D" ]
        then
            umount /boot
        fi
    fi
}

PACKAGE_ARCH := "${MACHINE_ARCH}"
FILES_${PN} = "/boot /usr/share /etc/init.d"

