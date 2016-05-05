SUMMARY = "Example of how to build an external Linux kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module machine_kernel_pr

PR = "r0"
PV = "0.5"

SRC_URI = "file://Makefile \
           file://aes256.c \
           file://aes256.h \
           file://bcm_au.c \
           file://bcm_au.rules \
           file://COPYING \
          "

S = "${WORKDIR}"
KV = "${KERNEL_VERSION}"

do_compile () {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
    oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/bcm-au" ' \
        'KERNEL_SOURCE="${STAGING_KERNEL_BUILDDIR}" ' \
        'KDIR="${STAGING_KERNEL_BUILDDIR}"' \
        'KERNEL_VERSION="${KERNEL_VERSION}" ' \
        'CC="${KERNEL_CC}" ' \
        'LD="${KERNEL_LD}" '

}

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

do_install() {
	if [ "${BRAND_OEM}" = "ini" ]; then
        	install -d ${D}/${sysconfdir}/modules-load.d
        	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/bcm
        
        	touch ${D}${sysconfdir}/modules-load.d/bcm.conf
	 	echo bcm >> ${D}/${sysconfdir}/modules-load.d/bcm.conf
        
        	for f in ${S}/*.ko; do
        		install -m 0644 $f ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/bcm;
        	done		
	else
        	install -d ${D}/${sysconfdir}/modules-load.d
        	install -d ${D}/lib/modules/${KV}/kernel/drivers/bcm
        
        	touch ${D}${sysconfdir}/modules-load.d/bcm.conf
	 	echo bcm >> ${D}/${sysconfdir}/modules-load.d/bcm.conf
        
        	for f in ${S}/*.ko; do
        		install -m 0644 $f ${D}/lib/modules/${KV}/kernel/drivers/bcm;
        	done
        fi	
}  
