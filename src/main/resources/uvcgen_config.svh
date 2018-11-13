`ifndef UVCGEN_CONFIG__SV
`define UVCGEN_CONFIG__SV

class uvcgen_config extends uvm_object;
    bit                     checks_enabled = 1;
    bit                     coverage_enabled = 1;
    uvm_active_passive_enum is_active = UVM_PASSIVE;

    `uvm_object_utils_begin(uvcgen_sequence_config)
    `uvm_field_int(checks_enabled, UVM_ALL_ON)
    `uvm_field_int(coverage_enabled, UVM_ALL_ON)
    `uvm_field_enum(is_active, UVM_ALL_ON)
    `uvm_object_utils_end

    function new (string name="uvcgen_config");
        super.new (name);
    endfunction : new

endclass : uvcgen_config

`endif //!UVCGEN_CONFIG__SV