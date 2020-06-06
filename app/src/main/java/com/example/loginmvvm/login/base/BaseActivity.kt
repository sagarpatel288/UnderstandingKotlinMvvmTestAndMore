package com.example.loginmvvm.login.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.loginmvvm.BR

/**
 * 12/3/2019
 * Check provided links to understand generics and @LayoutRes annotation
 * <p>
 * This is for abstraction. We have enforced the architecture rules that each activity that extends
 * this [BaseActivity] must have its viewModel, viewDataBinding and xml layout.
 * This is to reduce some boiler plate codes such as to get viewDataBinding, set lifeCycleOwner and
 * to set viewModel.
 * </p>
 *
 * @param VM Any viewModel that would extend [BaseVM].
 * That means, we are enforcing each activity that extends this [BaseActivity] to have associated viewModel
 * @param VDB DataBinding
 * That means, we are enforcing each activity that extend this [BaseActivity] to have layout tag in its
 * associated xml file to serve viewDataBinding/viewBinding purpose
 * @author srdpatel
 * @see <a href="https://www.journaldev.com/1663/java-generics-example-method-class-interface">Generics</a>
 * [Generics](https://www.journaldev.com/1663/java-generics-example-method-class-interface)
 * @since 1.0
 */
abstract class BaseActivity<VM : BaseVM, VDB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    AppCompatActivity() {

    abstract val viewModel: VM
    private lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }
}