`ifndef UTBGEN_MONITOR__SV
`define UTBGEN_MONITOR__SV

class utbgen_monitor extends uvm_monitor;
    typedef uvm_analysis_port#(utbgen_item) analysis_port;

    `uvm_component_utils(utbgen_monitor)

    function new (string name, uvm_component parent);
        super.new (name, parent);
    endfunction : new

endclass : utbgen_monitor

`endif //!UTBGEN_MONITOR__SV