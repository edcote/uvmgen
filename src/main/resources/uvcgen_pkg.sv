`ifndef UVCGEN_PKG__SV
`define UVCGEN_PKG__SV

package uvcgen_pkg;
    `include "uvm_macros.svh"

    import uvm_pkg::*;

    timeunit 1ns;
    timeprecision 1ps;

    `include "uvcgen_item.svh"
    `include "uvcgen_driver.svh"
    `include "uvcgen_monitor.svh"
    `include "uvcgen_agent.svh"

endpackage : uvcgen_pkg

`endif //!UVCGEN_PKG__SV