package com.sildeag.sound2text.uicommon.viewmodel
import androidx.lifecycle.ViewModel
import com.sildeag.sound2text.core.capabilities.DeviceCapabilities
import com.sildeag.sound2text.core.capabilities.FeatureCapabilities
import com.sildeag.sound2text.core.capabilities.isSupportedOn
import com.sildeag.sound2text.core.features.FeatureDescriptor
import com.sildeag.sound2text.core.features.FeatureRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CapabilityDebugViewModel(
    private val device: DeviceCapabilities,
    private val registry: FeatureRegistry
) : ViewModel() {
    data class FeatureCapabilityStatus(
        val descriptor: FeatureDescriptor,
        val capabilities: FeatureCapabilities,
        val supported: Boolean
    )
    private val _state =
        MutableStateFlow<List<FeatureCapabilityStatus>>(emptyList())
    val state = _state.asStateFlow()
    fun load() {
        val list =
            registry.descriptors.zip(registry.capabilities).map { (desc, caps) ->
                FeatureCapabilityStatus(
                    descriptor = desc,
                    capabilities = caps,
                    supported = caps.isSupportedOn(device)
                )
            }
        _state.value = list
    }
}
