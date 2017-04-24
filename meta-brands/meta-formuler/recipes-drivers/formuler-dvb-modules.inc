SUMMARY = "Hardware drivers for ${MACHINE}"
SECTION = "base"
PRIORITY = "required"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PV = "${KV}+${SRCDATE}"
PR = "r0"

SRC_URI = " \
	http://source.mynonpublic.com/formuler/${MACHINE}-drivers-${KV}-${SRCDATE}.zip \
	file://cdfs.ko \
	file://formuler1_4.ko \
"

S = "${WORKDIR}"

INHIBIT_PACKAGE_STRIP = "1"

do_compile() {
}
do_populate_sysroot() {
}


do_install() {
    install -d ${D}/lib/modules/${KV}/extra
    install -d ${D}/lib/modules/${KV}/extraci
    install -d ${D}/lib/modules/${KV}/extratc
    install -d ${D}/${sysconfdir}/modules-load.d
    for i in ${MACHINE}_1 ${MACHINE}_2 ${MACHINE}_3 ${MACHINE}_4; do
        install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extra
        echo $i >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf
        install -m 0755 ${WORKDIR}/$i.ko ${D}/lib/modules/${KV}/extraci
        echo $i >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_ci
    done
    install -m 644 ${WORKDIR}/cdfs.ko ${D}/lib/modules/${KV}/extratc
    install -m 644 ${WORKDIR}/formuler1_4tc.ko ${D}/lib/modules/${KV}/extratc/formuler1_4.ko
    echo "cdfs" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_tc
    echo "formuler1_4" >> ${D}/${sysconfdir}/modules-load.d/_${MACHINE}.conf_tc	
}

FILES_${PN} += "${sysconfdir}/modules-load.d/_${MACHINE}.conf ${sysconfdir}/modules-load.d/_${MACHINE}.conf_ci ${sysconfdir}/modules-load.d/_${MACHINE}.conf_tc /lib/modules/${KV}/extra /lib/modules/${KV}/extraci /lib/modules/${KV}/extratc" 
